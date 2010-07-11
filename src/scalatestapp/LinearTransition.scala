/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalatestapp

class LinearTransition(
  var value: Double, val vdelta: Double, callb: Option[(Double) => Unit]
) extends TransitionTrait {
  val start = value
  def tick(delta: Double, pos: Double) {
    value += delta * vdelta
    callb match {
      case Some(call) => call(value)
      case None =>
    }
  }
  
  def reset {
    value = start
  }

}
