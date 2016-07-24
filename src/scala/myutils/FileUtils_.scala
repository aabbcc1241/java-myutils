package myutils

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}

import scala.io.Source
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization._

/**
  * Created by beenotung on 7/24/16.
  */
object FileUtils_ {
  def loadJsonFromFile(path: String): Map[String, Any] = {
    implicit val formats = DefaultFormats
    parse(Source.fromFile(path).mkString).values.asInstanceOf[Map[String, Any]]
  }

  def writeJsonToFile(value: Map[String, Any], path: String): Unit = {
    implicit val formats = Serialization.formats(NoTypeHints)
    val text = write(value)
    writeToFile(text, path)
  }

  def writeToFile(text: String, path: String): Unit = {
    val w = new BufferedWriter(new FileWriter(new File(path)))
    w.write(text)
    w.close()
  }
}
