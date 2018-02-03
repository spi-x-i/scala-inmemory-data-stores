import sbt._

object Dependencies {

  private object typelevel {
    lazy val version   = "1.3.2"
    lazy val namespace = "com.typesafe"
    lazy val config       = namespace % "config" % version
  }

  private object redis {
    lazy val version = "2.9.0"
    lazy val namespace = "redis.clients"
    lazy val jedis = namespace % "jedis" % version
  }

  private object infinispan {
    lazy val version = "9.1.5.Final"
    lazy val namespace = "org.infinispan"
    lazy val remote = namespace % "infinispan-remote" % version
  }

  private object slf4j {
    lazy val version   = "1.7.25"
    lazy val versionLog4J = "1.6.1"
    lazy val namespace = "org.slf4j"
    lazy val api       = namespace % "slf4j-api" % version
    lazy val log4j = namespace % "slf4j-log4j12" % versionLog4J
  }

  private object log4j {
    lazy val version = "1.2.16"
    lazy val namespace = "log4j"
    lazy val logger = namespace % "log4j" % version
  }

  lazy val additionalDependencies = Seq(
    redis.jedis,
    infinispan.remote,
    slf4j.api,
    slf4j.log4j,
    log4j.logger,
    typelevel.config
  )

}
