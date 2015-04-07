(function (app) {
    var controller = function (barService) {
        this.caption = "Bist du hier:";
        this.bars = barService.getBars();
    };
    app.controller("BarChooserController", ["barService", controller]);
})(angular.module('durscht-core'));
