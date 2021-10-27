TARGET=FallRiskAdmin
JAVAC=javac
SOURCES=$(wildcard *.java)
OBJECTS=$(patsubst %.java,%.class,$(SOURCES))

all:
	$(JAVAC) $(SOURCES)

run: all
	java $(TARGET)

clean:
	rm -f $(OBJECTS)

.PHONY: clean all
