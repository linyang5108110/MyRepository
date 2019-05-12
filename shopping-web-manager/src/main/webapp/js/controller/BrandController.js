app.controller('brandController', function ($controller, $scope, brandService) {

    $controller("baseController", {$scope: $scope})

    //对象初始化，避免报错空指针异常
    $scope.searchEntity = {};
    //查询分页对象 入参 当前页 每页数 条件；条件对象
    $scope.search = function (currentPage, itemsPerPage) {
        brandService.search(currentPage, itemsPerPage, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }

    $scope.save = function () {
        var method = brandService.save($scope.entity);
        if ($scope.entity.id != null) {
            method = brandService.update($scope.entity);
        }
        method.success(
            function (response) {
                //判断是否成功保存
                if (response.flag) {
                    //刷新页面
                    alert(response.message);
                    $scope.reloadList();
                } else {
                    //提示
                    alert(response.message);
                }
            }
        )

    }

    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    }


    $scope.deleteIds = function () {

        if (confirm("您确定要删除吗？")) {
            brandService.delIds($scope.selectedIds).success(
                function (response) {
                    //判断是否删除保存
                    if (response.flag) {
                        //刷新页面
                        alert(response.message);
                        $scope.reloadList();
                    } else {
                        //提示
                        alert(response.message);
                    }
                }
            )
        }

    }

})