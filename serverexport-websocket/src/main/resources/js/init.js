var initWebsocket = function () {
  var websocket = new WebSocket("ws://localhost:8081/serverexport-websocket/websocket/socketServer.do");

  websocket.onmessage = function (result) {
    // debugger;
    console.info("websocket 返回报文:" + result.data);
    var resultData = JSON.parse(result.data);
    var commmandType = resultData.type;
    var commmandContent = resultData.content;
    // debugger;
    if(commmandType == 1){
      console.log("websocket 收到初始化命令");
      eval(commmandContent);
      console.log("websocket 执行命令完成");
    }else if(commmandType == 2){
      console.log("websocket 收到数据查询命令");
      var commandResult = eval(commmandContent)
      if (websocket.readyState == websocket.OPEN) {
        websocket.send(JSON.stringify(commandResult));
        console.log("websocket 发送返回值成功");
      } else {
        console.log("websocket 发送返回值失败,连接已经关闭");

      }
      console.log("websocket 执行命令完成");
    }else{
      console.log("websocket 未知类型的命令");
    }
  }

  websocket.onopen = function (result) {
    console.info("websocket onopen:");
  }
  websocket.onerror = function (result) {
    debugger;
    console.info("websocket onerror:" + result.data);
  }
  websocket.onclose = function (result) {
    debugger;
    console.info("websocket onclose:" + result.reason);
  }
  return websocket;
}

initWebsocket();
