BUS RIDER SIMULATION

1. Compilation:
   Navigate to the directory containing the Java files.
   Use the following command to compile all the Java files:
   javac *.java

2. Running the Program:
   After compilation, run the program with:
   java Main

3. Overview:
   This program simulates riders arriving at a bus stop and waiting for a bus. Buses arrive at an average interval defined by busIntervalMean while riders arrive at an average interval defined by riderIntervalMean. The bus has a capacity defined by busCapacity.

   The program uses semaphores for synchronization:
   - mutex: Ensures mutual exclusion when accessing shared resources.
   - multiplex: Limits the number of riders based on bus capacity.
   - bus: Signals the arrival of a bus.
   - allAboard: Indicates all riders have boarded the bus.

4. Note:
   Adjust the values of busIntervalMean and riderIntervalMean in the Main class if you want to see the results faster or slower.
