package org.spixi.redis.scala.inmemory

import java.nio.file.Path

import org.infinispan.client.hotrod.RemoteCacheManager
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder
import org.slf4j.Logger
import org.spixi.redis.scala.reader
import org.spixi.redis.scala.writer.Writer

object InfinispanServer {

  def infinispan(host: String, port: Int, filePath: Path)(implicit logger: Logger): Unit = {
    logger.info("Movie loading.")
    val byteArrayItem = reader.Reader.readBinaryFile(filePath)
    logger.info("Movie loaded.")

    logger.info("Connecting to infinispan remote server.")
    val infinispanConfiguration =
      new ConfigurationBuilder()
        .addServer()
        .host(host)
        .port(port)
        .build()

    val infinispan  = new RemoteCacheManager(infinispanConfiguration)
    val infiniCache = infinispan.getCache[String, Array[Byte]]()
    logger.info(s"Connected to infinispan at $host:$port")

    logger.info("Movie uploading on infinispan.")
    infiniCache.put("bigItem", byteArrayItem)
    logger.info("Movie uploaded on infinispan.")

    logger.debug("Should return a null value")
    val ret = infiniCache.get("simpleString") // if the key doesn't exists, returns a null
    logger.info(s"Redis not found key value is $ret")

    logger.info("Movie retrieving on infinispan.")
    val bigRet = infiniCache.get("bigItem")
    logger.info("Movie retrieved.")
    infiniCache.clear()
    infinispan.stop()

    val path = "/Users/aspina/Desktop/movie.mp4"
    Writer.writeToFile(path, bigRet)
    logger.info("Movie saved.")
  }

}
