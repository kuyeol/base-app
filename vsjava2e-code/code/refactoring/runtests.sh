#!/usr/bin/env ruby -W0
#---
# Excerpted from "Functional Programming in Java, Second Edition",
# published by The Pragmatic Bookshelf.
# Copyrights apply to this code. It may not be used to create training material,
# courses, books, articles, and the like. Contact us if you are in doubt.
# We make no guarantees that this code is fit for any purpose.
# Visit https://pragprog.com/titles/vsjava2e for more book information.
#---

test_files = `ls -a fpij/*Test.java  2>/dev/null | cat`.split(/\r?\n/)

test_files.each do |test_file|
  class_file =  test_file.sub('.java', '').sub('/', '.')

  puts '***************************************'
  puts class_file
  
  puts `java -classpath ../../lib/junit-jupiter-api-5.8.2.jar:../../lib/apiguardian-api-1.1.2.jar:../../lib/junit-platform-console-standalone-1.8.2.jar:../../lib/opentest4j-1.2.0.jar:bin org.junit.platform.console.ConsoleLauncher -c #{class_file}`
end



