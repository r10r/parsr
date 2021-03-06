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
  
  def filetask(options = {})
    files = Dir.glob(options[:files])
    if (files.empty?)
      puts "\n#{options[:message_not_found]}\n"
    else
      puts "\n#{options[:message_found]}"
      files.each do |path|
        if yield(path)
          puts("  processed file: #{path}")
        end
	    end
      puts "\n"
	  end
  end

  # generate all java classes for the ragel files in the /src directory recursively
  # which have a machine definition and a machine instantiation
  task :ragel do
    # check that the ragel file contains a machine instantiation
    filetask(:files => 'src/**/*.rl',
             :message_not_found => "No machine definitions found!",
             :message_found => "Generating ragel parsers for machine definitions:") do |path|
      if File.new(path).grep(/(^|[\s]+)machine[\s]+/).length > 0 && 
         File.new(path).grep(/.*:=.*/).length > 0
        system("ragel -J #{path}")
      end
    end
  end

  # remove all generated ragel parsers from the /src directory recursively
  task :ragel_clean do
    filetask(:files => "src/**/*.rl",
             :message_not_found => "No generated ragel parsers found to delete!",
             :message_found => "Removing generated ragel parsers:") do |path|
      path = path.gsub(/\.rl$/,'.java')
      File.delete(path) if File.exists?(path)
    end
  end

end

