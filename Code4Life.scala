import java.util._
import java.io._
import java.math._


object Player {

  // This method for bestSample.id input in "data" part.
  def gotoAndConnect(module: String, data: Int, position: String): Unit = {
      
    // If my position is at the desired module:
    if (position == module) {
      println("CONNECT " + data)
    } 
    // If my position is not at the correct module:
    else{
        println("GOTO " + module)
    }
  }

  // This method for neededMolecule input in "data" part.
  def gotoAndConnect(module: String, data: String, position: String): Unit = {

    // If my position is at the desired module:
    if (position == module) {
      println("CONNECT " + data)
    } 
    // If my position is not at the correct module:
    else{
        println("GOTO " + module)
    }
  }

  def main(args: Array[String]): Unit = {

    val in: Scanner = new Scanner(System.in)
    val projectCount: Int = in.nextInt()

    for (i <- 0 until projectCount) {
      val a: Int = in.nextInt()
      val b: Int = in.nextInt()
      val c: Int = in.nextInt()
      val d: Int = in.nextInt()
      val e: Int = in.nextInt()
    }

    class Sample(var id: Int,
                 var carriedBy: Int,
                 var rank: Int,
                 var gain: String,
                 var health: Int,
                 var costs: ArrayList[Integer]) {

      def getId(): Int = this.id

    }

    class Robot(var storage: ArrayList[Integer], var target: String)
// game loop
    while (true) {
     
      // Here are my "samples" and "robots" arrays:
      val samples: ArrayList[Sample] = new ArrayList[Sample]()
      val robots: ArrayList[Robot] = new ArrayList[Robot]()

      for (i <- 0.until(2)) {
        val target: String = in.next()
        val eta: Int = in.nextInt()
        val score: Int = in.nextInt()
        val storageA: Int = in.nextInt()
        val storageB: Int = in.nextInt()
        val storageC: Int = in.nextInt()
        val storageD: Int = in.nextInt()
        val storageE: Int = in.nextInt()
        val storages: ArrayList[Integer] = new ArrayList[Integer]()
        storages.add(storageA)
        storages.add(storageB)
        storages.add(storageC)
        storages.add(storageD)
        storages.add(storageE)
        val expertiseA: Int = in.nextInt()
        val expertiseB: Int = in.nextInt()
        val expertiseC: Int = in.nextInt()
        val expertiseD: Int = in.nextInt()
        val expertiseE: Int = in.nextInt()
        robots.add(new Robot(storages, target))
      }
      val availableA: Int = in.nextInt()
      val availableB: Int = in.nextInt()
      val availableC: Int = in.nextInt()
      val availableD: Int = in.nextInt()
      val availableE: Int = in.nextInt()
      val sampleCount: Int = in.nextInt()
      for (i <- 0 until sampleCount) {
        val sampleId: Int = in.nextInt()
        val carriedBy: Int = in.nextInt()
        val rank: Int = in.nextInt()
        val expertiseGain: String = in.next()
        val health: Int = in.nextInt()
        val costA: Int = in.nextInt()
        val costB: Int = in.nextInt()
        val costC: Int = in.nextInt()
        val costD: Int = in.nextInt()
        val costE: Int = in.nextInt()
        val costs: ArrayList[Integer] = new ArrayList[Integer]()
        costs.add(costA)
        costs.add(costB)
        costs.add(costC)
        costs.add(costD)
        costs.add(costE)
        val sample: Sample = new Sample(sampleId, carriedBy, rank, expertiseGain, health, costs)
        samples.add(sample)
      }

      val pinarkok: Robot = robots.get(0)
      var bestSample: Sample = null
      var maxHealth: Int = 0

      // Choosing the sample that has the max health:
      // "!= 1" means it is in the cloud.
      for (i <- 0 until samples.size if samples.get(i)
             .health > maxHealth && samples.get(i).carriedBy != 1) {
        bestSample = samples.get(i)
        maxHealth = samples.get(i).health
      }

      // If I am not carrying it I want to go to the machine:
      if (bestSample.carriedBy != 0) {
        gotoAndConnect("DIAGNOSIS", bestSample.getId, pinarkok.target)
      }
      // If I am holding the molecules I need:
      else{
        var neededMolecule: String = ""
        val molecules: Array[String] = Array("A", "B", "C", "D", "E")

        // Let's iterate over each molecule:
        // As soon as I find one molecule that I need I just connect:
        for (i <- 0.until(5)
            // If my storage is smaller than the cost of my sample for each molecule type:
            if pinarkok.storage.get(i) < bestSample.costs.get(i)) {
            // I connect then neeeded molecule:
            neededMolecule = molecules(i)
            //break
            }
            // If there is molecule needed more, go to MOLECULES to collect these:
            if (neededMolecule.length != 0) {
            gotoAndConnect("MOLECULES", neededMolecule, pinarkok.target)
            }
            // If there is no molecule needed more, go to LABORATORY:
            else {
            gotoAndConnect("LABORATORY", bestSample.id, pinarkok.target)
            }
        }
    }
  }
}
