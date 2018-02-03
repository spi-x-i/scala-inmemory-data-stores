package org.spixi.redis.scala.writer

import java.io.{File, FileOutputStream}

object Writer {

  def writeToFile(path: String, toBeWrited: Array[Byte]): Unit = {
    val file = new File(path)
    val fos  = new FileOutputStream(file)

    fos.write(toBeWrited)

    fos.close()
  }

}
