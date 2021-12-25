
function queryApp() {
  var apps = [];
  jQuery.ajax({
    "type": "get", "async": false,
    "url": "exportData",
    "success": function (resp) {
      console.info("queryApp 查询结果:" + JSON.stringify(apps))
      apps = resp;
    }, "error": function (resp) {
      //debugger;
      console.error("queryApp 查询异常:" + resp)
    }
  });
  return apps;
}
queryApp();
