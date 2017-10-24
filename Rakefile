task default: [:compile, :install, :gem]

desc 'Create Manifest'
task :init do
  create_manifest
end

desc 'Install'
task :install do
  sh 'mv target/skatolo.jar ruby/lib'
end

desc 'Gem'
task :gem do
  sh 'cd ruby && gem build skatolo.gemspec'
end

desc 'Compile'
task :compile do
  sh 'mvn package'
end

desc 'clean'
task :clean do
  Dir['./**/*.%w{jar gem}'].each do |path|
    puts 'Deleting #{path} ...'
    File.delete(path)
  end
  FileUtils.rm_rf('./target')
end
