
import java.io.{BufferedWriter, File, FileWriter}
import sbt.Keys._
import sbt.{Build, _}

object LabelledReleaseBuild extends Build {

  val buildDescriptor = new BuildDescriptor

  lazy val buildInfo = TaskKey[Unit]("gen-build-desc")
  lazy val buildInfoTask = (baseDirectory in Compile) map { dir =>
    val outputDir = (dir / "public").getPath
    val targetFile = new File(outputDir, "build-info.txt")
    writeFile(targetFile, format(buildDescriptor.buildData()))
  }

  /**
   * Writes data to targetFile.
   * @param targetFile the destination file.
   * @param data the data to be written to the specified file.
   */
  private def writeFile(targetFile: File, data: String) = {
    targetFile.delete()
    val fw = new FileWriter(targetFile)
    val bw = new BufferedWriter(fw)
    bw.write(data)
    bw.close()
  }

  /**
   * Formats a list of tuples into lines in the build descriptor format.
   * @param buildData a List of descriptor entry tuples.
   * @return a string representation of the input data.
   */
  private def format(buildData: List[(String, Any)]): String = {
    val lineSeparator = System.getProperty("line.separator")
    buildData.map{ case(key, value) => s"$key: $value"} mkString lineSeparator
  }
}