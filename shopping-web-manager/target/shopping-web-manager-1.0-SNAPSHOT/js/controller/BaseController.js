app.controller("baseController", function ($scope) {

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 5,
        perPageOptions: [5, 10, 20, 22],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    $scope.selectedIds = [];
    // $event.target 这个就等价于js中的this
    $scope.updateSelected = function ($event, id) {
        if ($event.target.checked)//如果复选框选中就将这个选中的id放到数组中
        {
            $scope.selectedIds.push(id);
        } else {
            //否者就是没取消选中就将id从数组中删除
            //选获得id在数组数组中的下标
            var index = $scope.selectedIds.indexOf(id);
            $scope.selectedIds.splice(index, 1);
        }
    }

    //重新加载
    $scope.reloadList = function () {
        //调用分页查询
        // $scope.findpage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    //定义方法：获取JSON字符串中的某个key对应的值的集合
    $scope.jsonToString = function (jsonStr,key) {
        //将JSON字符串转换成JSON对象 转换后的就成了 []
        var jsonObj = JSON.parse(jsonStr);
        var value = "";

        for(var i=0; i<jsonObj.length ;i++)
        {
            if(i>0)
            {
                value +=",";
            }
            value += jsonObj[i][key];
            //注意这个地方[key]因为我们取值拿属性时候是个变量，可以直接写死.text但是方法就没什么价值了

        }
        return value;
    }

})