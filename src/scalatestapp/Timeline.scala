/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalatestapp


import scala.collection.mutable._

import compat.Platform.currentTime


class Timeline(
  var transitions: Buffer[(ContinousRange, TransitionTrait)] = new ListBuffer,
  var loop: Boolean = false
) {
  var starttime: Long = 0;
  var lasttick: Long = 0;
  var enabled = false;
  var last = 0;

  def reset {
    starttime = 0;
    lasttick = 0;
    enabled = false;
    last = 0;

    for (trans <- transitions) {
      trans._2.reset()
    }
  }

  def start {
    starttime = currentTime
    lasttick = currentTime
    enabled = true
    
    var maximum = 0.0
    var n = 0.0

    for (i <- transitions) {
      n = i._1.stop
      if (n > maximum) {
        maximum = n
      }
    }
  }
  
  def tick {
    val t = currentTime
    // Convert ms to seconds. Is this a good idea?
    val pos = (t - starttime) / 1000.0
    val lastpos = (lasttick - starttime) / 1000.0

    val delta = new ContinousRange(lastpos, pos)

    for ((range, trans) <- transitions) {
      var inters = delta.intersect(range)
      
      inters match {
        case Some(int) => trans.tick(int.magnitude, pos)
        case None =>
      }
    }
    lasttick = t
    if (loop && pos > last) {
      for ((range, trans) <- transitions) {
        trans.reset
      }
      starttime = t
    }
  }

  def addTransistion(ran: ContinousRange, trans:  TransitionTrait) {
    transitions += ((ran, trans))
  }

}
