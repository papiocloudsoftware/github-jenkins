package com.papiocloud.pipelines.plugin.steps;

import com.papiocloud.pipelines.plugin.AbstractJenkinsSpec

class PushToRemoteStepSpec extends AbstractJenkinsSpec {

    def "will fail if no git scm on job"() {
        when:
        createAndRunJob("""
node {
  gitPush()
}
""")
        then:
        def e = thrown(IOException)
        e.message == "GitSCM not found on run"
    }

    def "will fail if configured remote not found"() {
        when:
        createAndRunJob("""
node {
  git("${mockGitRepo.toURI()}")
  gitPush(remote: "mock")
}
""")
        then:
        def e = thrown(IOException)
        e.message == "Remote with name 'mock' not found"
    }
}
