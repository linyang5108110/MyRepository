app.service("brandService", function ($http) {

    this.search = function (currentPage, itemsPerPage, searchEntity) {
        return $http.post("/brand/search.do?pageNum=" + currentPage + "&pageSize=" +
            itemsPerPage, searchEntity);
    }

    this.save = function (entity) {
        return $http.post("/brand/save.do", entity);
    }

    this.update = function (entity) {
        return $http.post("/brand/update.do", entity)
    }

    this.findOne = function (id) {
        return $http.get("/brand/findOne.do?id=" + id);
    }

    this.delIds = function (selectedIds) {
        return $http.get("/brand/deleteIds.do?ids=" + selectedIds)
    }
    this.selectOptionList =function () {
        return $http.get("/brand/findList.do")
    }
})