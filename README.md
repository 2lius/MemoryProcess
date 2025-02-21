# Windows进程内存操作
<code lang="java">
  // 获取进程
  MemoryProcess counterStrikeProcess = new MemoryProcess("Counter-Strike");
  // 获取进程id
  System.out.printf("获取进程id:%d\r\n", counterStrikeProcess.getProcessId());
  // 打开进程
  if(counterStrikeProcess.openProcess()){
      System.out.println("打开进程成功");
      // 读取内存
      float bloodValue = counterStrikeProcess.readFloat(0x25069bc, 0x7c, 0x4, 0x160);
      System.out.println("血量：" + bloodValue);
      // 写入内存
      counterStrikeProcess.writeFloat(100, 0x25069bc, 0x7c, 0x4, 0x160);
      // 关闭进程
      counterStrikeProcess.closeProcess();
      System.out.println("关闭进程句柄！！");
  };
<code>
