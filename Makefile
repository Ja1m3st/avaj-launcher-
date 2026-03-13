# ! Quitarlo antes de entregar
MAIN_CLASS = ro.academyplus.avaj.simulator.Simulator
ARG = scenario.txt

all:
	@find * -name "*.java" > sources.txt && javac @sources.txt && java ro.academyplus.avaj.simulator.Simulator scenario.txt && find . -name "*.class" -delete  


.PHONY: all