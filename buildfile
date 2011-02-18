# Generated by Buildr 1.4.4, change to your liking
# Standard maven2 repository
repositories.remote << 'http://www.ibiblio.org/maven2'

require 'time'

THIS_VERSION = '0.1'

layout = Layout.new
layout[:source, :main, :java] = 'src/main/java'
layout[:source, :test, :java] = 'src/test/java'

Project.local_task :ragel
Project.local_task :ragel_clean


desc 'codeblockparser'
define 'codeblockparser', :layout=>layout do
  eclipse.natures 'org.eclipse.jdt.core.javanature'
  eclipse.builders 'org.eclipse.jdt.core.javabuilder'
  compile.with 'commons-io:commons-io:jar:2.0'
  #test.with 'junit:junit:jar:4.8.2'
  test.with 'org.mockito:mockito-core:jar:1.8.5'
  compile.with( 
    'org.slf4j:slf4j-log4j12:jar:1.6.1',
    'org.slf4j:slf4j-api:jar:1.6.1',
    'log4j:log4j:jar:1.2.16'
  )

  project.group = 'de.entwicklerland'
  project.version = THIS_VERSION
  package :sources
  package :javadoc
  package(:jar).with :manifest=>
  { 
    'Project' => project.id,
    'Copyright' => 'Ruben Jenster (C) 2011',
    'Version' => THIS_VERSION,
    'Creation' => Time.now.strftime("%a, %d %b %Y %H:%M:%S %z")
  }

  # generate all java classes for the ragel files in the /src directory recursively
  task :ragel do
    # check that the ragel file contains a machine instantiation
    file_names = Dir.glob('src/**/*.rl').select {|name| File.new(name).grep(/.*:=.*/).length > 0}
    if (file_names.empty?)
      puts "\nNo machine definitions found!\n"
    else
      puts "\nGenerating ragel parsers for machine definitions:"
      file_names.each do |name|
        system("ragel -J #{name}")
        puts("  file: #{name}")
	    end
	  end
      puts "\n"
  end

  # remove all generated ragel parsers from the /src directory recursively
  task :ragel_clean do
    
    file_names = Dir.glob("src/**/*.rl").collect {|name| name.gsub('.rl','.java')}
    file_names = file_names.select {|name| File.exists? name }
    
    if (file_names.empty?)
      puts "\nNo generated ragel parsers found to delete!"
    else
      puts "\nRemoving generated ragel parsers:"
      file_names.each do |name| 
        if File.exists?(name)
         puts("file: #{name}")
         File.delete(name)
        end
      end
	  end
      puts "\n"
  end



end

