# ! Quitarlo antes de entregar
MAIN_CLASS = ro.academyplus.avaj.simulator.Simulator
ARG = scenario.txt

all:
	@mkdir -p out && find * -name "*.java" > sources.txt && javac -d out @sources.txt && java -cp out ro.academyplus.avaj.simulator.Simulator scenario.txt 


.PHONY: all