package com.solr.dataLoading;

import com.alibaba.fastjson.JSON;
import com.shopping.core.dao.item.ItemDao;
import com.shopping.core.pojo.item.Item;
import com.shopping.core.pojo.item.ItemQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/*.xml"})
public class SolrDataLoading {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * //数据导入
     *
     * @Test public void dataImport() throws  Exception {
     * //条件：
     * ItemQuery itemQuery = new ItemQuery();
     * //
     * List<Item> itemList =
     * itemDao.selectByExample(itemQuery);
     * <p>
     * for (Item item : itemList) {
     * String spec = item.getSpec();
     * item.setSpecMap(JSON.parseObject(spec,Map.class));
     * }
     * <p>
     * //
     * solrTemplate.saveBeans(itemList,1000);
     * <p>
     * }
     */
    @Test
    public void testItemDataLoadin() {


        /**
         * 因为规格这个东西每个商品都不一样
         * 所以这里用到了动态field(域)
         *  在item中规格是spec他的数据是{"机身内存":"16G","网络":"联通3G"}这样的是字符串
         *  不能对应到solr数据中
         *  因此我们定义一个map并且打上标签  @Dynamic
         *                               @Field("item_spec_*")
         *                               private Map<String,String> specMap;
         *  表明动态域，然后我们在查询到数据后循环spec将他转换成JSON对象MAP直接将数据放到map中
         *  这样一保存数据solr中就有了规格数据，热切是动态的
         */
        ItemQuery itemQuery = new ItemQuery();
        List<Item> itemList = itemDao.selectByExample(itemQuery);

        for (Item item : itemList) {
            item.setSpecMap(JSON.parseObject(item.getSpec(), Map.class));
        }
        solrTemplate.saveBeans(itemList, 1000);

    }

}
