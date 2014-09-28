package com.github.dextaa.label.env

import play.api.test.{FakeApplication, Helpers, TestServer}

trait GlobalServer {
  val listenPort = RunningPlayServer.Port
  val playServer = RunningPlayServer.ServerInstance
}


private object RunningPlayServer {
  val Port = Helpers.testServerPort + 1
  val ServerInstance = TestServer(Port, FakeApplication())
  ServerInstance.start()
}