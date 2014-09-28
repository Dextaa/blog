
  import org.eclipse.jgit.api.Git
  import org.eclipse.jgit.lib._
  import org.eclipse.jgit.revwalk.{RevCommit, RevTag, RevWalk}
  import org.eclipse.jgit.storage.file.FileRepositoryBuilder
  import org.joda.time.DateTime
  import scala.collection.mutable
  import scala.util.Properties
  import scala.collection.JavaConverters._

  /**
   * Queries the head revision of the current branch and generates
   * source revision (and build machine environment) metadata.
   */
  class BuildDescriptor {

    val repositoryBuilder = new FileRepositoryBuilder
    val repository = repositoryBuilder.readEnvironment.findGitDir.build
    val git = new Git(repository)

   /**
    * Creates the build descriptor entries.
    * @return a list of tuples, each containing a descriptor entry.
    */
    def buildData() = {

      val status = git.status.call
      val uncommittedChanges = ! status.isClean
      val branch = repository.getBranch

      val (revision, tag) = gitHeadRevisionAndTag()

      List(
        "Git branch" -> branch,
        "Git head revision" -> revision,
        "Git head tag" -> tag.getOrElse("Not tagged"),
        "Git branch had uncommitted changes" -> uncommittedChanges,
        "Built by" -> Properties.userName,
        "Built at" -> DateTime.now().toString("dd-MM-YYYY HH:mm:ss"),
        "Built using Java version" -> Properties.javaVersion,
        "Built using Scala version" -> play.core.PlayVersion.scalaVersion,
        "Built using Play version" -> play.core.PlayVersion.current
      )
    }

   /**
    * Gets the revision and optionally the tag at the head of the subject branch.
    * @return a tuple in the form (head revision hash, optional head revision tag)
    */
    def gitHeadRevisionAndTag() = {

      val headRevisionId = repository.getRef(Constants.HEAD).getObjectId
      def matches(id: ObjectId) = id == headRevisionId

      def matchesHead(tag: Ref) = {
        val walk = new RevWalk(repository)
        val revObject = walk.parseAny(tag.getObjectId)
        revObject match {
          case rc: RevCommit => matches(tag.getObjectId) //Matches lightweight tags.
          case rt: RevTag => matches(rt.getObject.getId) //Matches annotated tags (the extra indirection is
        }                                                //required because tag.getObjectId returns the tag's
      }                                                  //object id, not the associated commit).

      val allTags: mutable.Buffer[Ref] = git.tagList().call().asScala
      val currentTag = allTags.find(matchesHead).
        map{ tag => Repository.shortenRefName(tag.getName)}
      ObjectId.toString(headRevisionId) -> currentTag
    }
  }

