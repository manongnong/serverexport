@java -cp "h2-1.4.200.jar;%H2DRIVERS%;%CLASSPATH%" org.h2.tools.Shell %* 
@if errorlevel 1 pause