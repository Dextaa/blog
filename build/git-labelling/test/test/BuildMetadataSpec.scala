package test

import com.github.dextaa.label.env.GlobalServer
import org.scalatest.{FeatureSpecLike, GivenWhenThen, Matchers}
import play.api.Play.current
import play.api.libs.ws.WS

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * This is just to provide confidence that the base app is working properly. As it's the build process
 * rather than the app itself that's of interest, this test can be ignored.
 */
class BuildMetadataSpec extends FeatureSpecLike with GlobalServer with GivenWhenThen with Matchers {

  private val maxWait = Duration(1, SECONDS)
  private val gitRevisionRegex = "^Git head revision: [0-9a-fA-F]{40}$".r

  feature("the system should allow all users read-only access to deployment metadata") {

    scenario("a user can read the build descriptor generated during the build process") {
      val response = Await.result(WS.url(s"http://localhost:$listenPort/assets/build-info.txt").get(), maxWait)
      response.status should be (200)
      verifyMetadata(response.body)
    }

  }

  private def verifyMetadata(metadata: String) = {
    val items = metadata.split(System.getProperty("line.separator")).toVector
    items.contains("Git branch: master") should be (true)
    items.exists(_.startsWith("Git head tag: ")) should be (true)
    items.exists(t => gitRevisionRegex.findFirstIn(t).isDefined) should be (true)
    items.contains("Built using Play version: 2.3.4") should be (true)
    //etc..
  }

}