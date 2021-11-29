TARGET=FallRiskAdmin
JAVAC=javac
JAVA=java
SOURCES=$(wildcard *.java)
OBJECTS=$(patsubst %.java,%.class,$(SOURCES))

all:
	$(JAVAC) $(SOURCES)

clean:
	rm -f $(OBJECTS)

.PHONY: clean all
