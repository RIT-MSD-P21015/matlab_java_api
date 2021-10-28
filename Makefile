TARGET=FallRiskAdmin
JAVAC=/opt/jdk1.8.0_202/bin/javac
JAVA=/opt/jdk1.8.0_202/bin/java
SOURCES=$(wildcard *.java)
OBJECTS=$(patsubst %.java,%.class,$(SOURCES))

all:
	$(JAVAC) $(SOURCES)

run: all
	$(JAVA) $(TARGET)

clean:
	rm -f $(OBJECTS)

.PHONY: clean all
