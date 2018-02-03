package org.spixi.redis.scala.reader

import java.nio.file.{Files, Path, Paths}

object Reader {

  def getPath(stringPath: String): Path =
    Paths.get(stringPath)

  def readBinaryFile(filePath: Path): Array[Byte] =
    Files.readAllBytes(filePath)

}
