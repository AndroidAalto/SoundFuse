all: debug
run: run-debug

run-debug: debug
	adb shell am start -a android.intent.action.MAIN -n org.androidaalto.soundfuse/.BoardActivity

debug: build.xml
	ant debug

release: build.xml
	ant release

build.xml:
	android update project -n `basename $$PWD` -p .

clean:
	git clean -Xdf
