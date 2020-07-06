
(function () {

    var switcher = document.querySelector(".page_switcher");

    var routing = new Routing({
        mainCmp: switcher,
        mainCmpCls: "page_switcher"
    });

    utils.routing = routing;

    document.addEventListener("DOMContentLoaded", function () {
        routing.route("/");

        var languagePanel = document.querySelector(".language_panel select");
        languagePanel.addEventListener("change", function(evt) {
            utils.activeLanguage = evt.target.value;
            utils.routing.route("/");
        });
    });



})();
