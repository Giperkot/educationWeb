(function () {
    function Rouuting (config) {
        this.mainCmp = config.mainCmp;
        this.mainCmpCls = config.mainCmpCls;
    }

    Rouuting.prototype.route = function (routeStr) {
        var self = this;

        var urlParts = routeStr.split("/");

        switch (urlParts[1]) {
            case "":
            case "main":
                // Запрос на specialization

                utils.getHttpPromise({
                    method: "POST",
                    url: utils.host + "/api/private/test/getSpecializationList",
                    contentType: "application/json",
                    jsonData: {
                        language: utils.activeLanguage
                    }
                }).then(function (response) {

                    var data = JSON.parse(response);
                    console.log(data);

                    var page = cmpCore.addElement({
                        name: "mainPage",
                        type: "CMainPage",
                        container: "." + self.mainCmpCls,
                        children: [
                            {
                                name: "specializationList",
                                type: "CSpecialization",
                                container: ".specialization_list",
                                model: {
                                    specializationList: data
                                }
                            }
                        ]
                    });

                    cmpCore.drawAll();
                });


                break;
            case "specialization":
                var courseId = urlParts[2];

                utils.getHttpPromise({
                    method: "POST",
                    url: utils.host + "/api/private/test/getFullSpecialization",
                    contentType: "application/json",
                    jsonData: {
                        language: utils.activeLanguage,
                        specializationId: courseId
                    }
                }).then(function (response) {

                    var data = JSON.parse(response);
                    console.log(data);

                    var page = cmpCore.addElement({
                        name: "specializationMainPage",
                        type: "CSpecializationMain",
                        container: "." + self.mainCmpCls,
                        model: {
                            specializationInfo: data
                        },
                        children: [
                            {
                                name: "coursesList",
                                type: "CCoursesList",
                                container: ".courses_list",
                                model: {
                                    specializationInfo: data
                                }
                            }
                        ]
                    });

                    cmpCore.drawAll();
                });

                break;
            case "course":
                var courseId = urlParts[2];

                utils.getHttpPromise({
                    method: "POST",
                    url: utils.host + "/api/private/test/getFullCourseInfo",
                    contentType: "application/json",
                    jsonData: {
                        language: utils.activeLanguage,
                        specializationId: courseId
                    }
                }).then(function (response) {

                    var data = JSON.parse(response);
                    console.log(data);

                    var page = cmpCore.addElement({
                        name: "specializationMainPage",
                        type: "CCourseDetails",
                        container: "." + self.mainCmpCls,
                        model: data
                    });

                    cmpCore.drawAll();
                });
                break;
            case "/search":

                break;
        }





    };

    window.Routing = Rouuting;
})();