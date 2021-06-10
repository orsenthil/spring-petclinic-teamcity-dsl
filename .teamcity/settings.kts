import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

version = "2021.1"

project {

    vcsRoot(PetClinicVcsRoot)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(PetClinicVcsRoot)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    triggers {
        vcs {
        }
    }
})

object PetClinicVcsRoot: GitVcsRoot({
    name = "https://github.com/orsenthil/spring-petclinic#refs/heads/main"
    url = "https://github.com/orsenthil/spring-petclinic"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "mesosphere-ci"
        password = "credentialsJSON:ee638c87-f72c-4806-b15d-719c5cb93455"
    }
})