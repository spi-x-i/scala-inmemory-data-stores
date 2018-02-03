package org.spixi.redis.scala

import com.typesafe.config.ConfigFactory
import org.slf4j.{Logger, LoggerFactory}
import org.spixi.redis.scala.inmemory.{InfinispanServer, RedisServer}

object Startup {

  private lazy val FileIndices = Map(
    "tiny~512M"  -> "inmemory.sample.filepath",
    "avg~750M"   -> "inmemory.sample.midfilepath",
    "huge~1000M" -> "inmemory.sample.bigfilepath"
  )

  def main(args: Array[String]): Unit = {

    implicit val logger: Logger = LoggerFactory.getLogger(Startup.getClass)

    val config = ConfigFactory.load()

    val host     = config.getString("inmemory.redis.host")
    val filePath = config.getString(FileIndices("tiny~512M"))

    val path = reader.Reader.getPath(filePath)

    // RedisServer.redis(host, 6379, path)
    InfinispanServer.infinispan(host, 11222, path)
  }

}
