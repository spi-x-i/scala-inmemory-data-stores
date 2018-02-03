package org.spixi.redis.scala.inmemory

import java.nio.file.Path

import org.slf4j.Logger
import org.spixi.redis.scala.{reader, writer}
import redis.clients.jedis.Jedis

object RedisServer {

  private def connect(host: String, port: Int) =
    new Jedis(host, port)

  def redis(host: String, port: Int, filePath: Path)(implicit logger: Logger): Unit = {
    logger.info("Movie loading.")
    val byteArrayItem = reader.Reader.readBinaryFile(filePath)
    logger.info("Movie loaded.")

    logger.info("Connecting to redis remote server.")
    val redis = connect(host, port)
    logger.info(s"Connected to redis at $host:$port")

    logger.info("Movie uploading.")

    redis.set("bigItem".getBytes, byteArrayItem)
    redis.expire("bigItem".getBytes, 1)
    logger.info("Movie uploaded.")

    // redis.del("simpleString")

    logger.debug("Should return a null value")
    val ret = redis.get("simpleString") // if the key doesn't exists, returns a null
    logger.info(s"Redis not found key value is $ret")

    logger.info("Movie retrieving.")
    val bigRet = redis.get("bigItem".getBytes)
    logger.info("Movie retrieved.")
    redis.close()

    val destPath = "/Users/aspina/Desktop/movie.mp4"

    writer.Writer.writeToFile(destPath, bigRet)
    logger.info("Movie saved.")
  }

}
