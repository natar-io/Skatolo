project 'skatolo', 'https://github.com/potioc/Skatolo' do

  model_version '4.0.0'
  id 'tech.lity.rea:skatolo:1.1'
  packaging 'jar'

  description 'Skatolo, GUI for Processing.'

  { 'sojamo' => 'Andreas Schlegel', 'poqudrof' => 'Jeremy Laviole' }.each do |key, value|
    developer key do
      name value
      roles 'developer'
    end
  end

  source_control( :url => 'https://github.com/Rea-lity-Tech/Skatolo',
                  :tag => '5740a45f76b1df7001935b430bf78b165ce3b86e' )

  distribution do
    repository( :id => 'ossrh',
                :url => 'https://oss.sonatype.org/service/local/staging/deploy/maven2/' )
    snapshot_repository( :id => 'ossrh',
                         :url => 'https://oss.sonatype.org/content/repositories/snapshots' )
  end

  properties(
     'maven.compiler.source' => '1.8',
     'project.build.sourceEncoding' => 'UTF-8',
     'maven.compiler.target' => '1.8',
     'polyglot.dump.pom' => 'pom.xml',
     'processing.api' => 'http://processing.github.io/processing-javadocs/core/'
   )

  jar 'org.processing:core:3.3.6'

  plugin( :jar, '2.3.2',
          'finalName' =>  'skatolo' )
  plugin( :compiler, '2.3.2',
          'source' =>  '${maven.compiler.source}',
          'target' =>  '${maven.compiler.target}' )
  plugin( :javadoc, '2.10.1',
          'additionalparam' =>  '-Xdoclint:none' ) do
    execute_goals( 'jar',
                   :id => 'attach-javadocs' )
  end

  plugin :jar, '2.6'
  plugin :source, '2.4' do
    execute_goals( 'jar',
                   :id => 'attach-sources' )
  end

  plugin :gpg, '1.6' do
    execute_goals( 'sign',
                   :id => 'sign-artifacts',
                   :phase => 'verify' )
  end

  plugin( 'org.sonatype.plugins:nexus-staging-maven-plugin:1.6.8', :extensions => true,
          'serverId' =>  'ossrh',
          'nexusUrl' =>  'https://oss.sonatype.org/',
          'autoReleaseAfterClose' =>  'true' )

  build do
    source_directory 'src'
    test_source_directory 'test'
  end

  profile 'release-sign-artifacts' do

    activation do
      property( :name => 'performRelease',
                :value => 'true' )
    end

    plugin :gpg, '1.6' do
      execute_goals( 'sign',
                     :id => 'sign-artifacts',
                     :phase => 'verify' )
    end

  end

end
