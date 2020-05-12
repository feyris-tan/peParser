a.exe : main.o testres.res
	g++ -mwindows main.o testres.res -o $@
	strip $@

testres.res : testres.rc
	windres $< -O coff -o $@

clean:
	-rm testres.res
	-rm a.exe
	-rm main.o

