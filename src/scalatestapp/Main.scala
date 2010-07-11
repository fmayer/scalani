/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalatestapp

import scala.collection.mutable.ListBuffer

import compat.Platform.currentTime



object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    val a = new ContinousRange(0.0, 2.0)
    val b = new ContinousRange(3.0, 4.0)

    // println(a.intersect(b))

    val c = new ContinousRange(0.0, 4.0)
    val d = new ContinousRange(1.0, 2.0)

    // println(c.intersect(d))

    val x = new ListBuffer[(ContinousRange, TransitionTrait)]
    x += ((c, new LinearTransition(0, 5, None)))

    var t = new Timeline(x, false)
    t.start
    println("Hello, world!")

    val lt = new LinearTransition(0, 1, None)
    val tl = new Timeline()

    tl.addTransistion(new ContinousRange(2, 20), lt)
    tl.start

    var st = currentTime

    while (currentTime - 30000 < st){
        tl.tick
        println(lt.value)
        Thread.sleep(1300)
    }
  }

}
