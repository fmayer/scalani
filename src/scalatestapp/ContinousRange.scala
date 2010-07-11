/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalatestapp

class ContinousRange(var start: Double, var stop: Double) {
  def ==(other: ContinousRange) =
    (start == other.start && stop == other.stop)
  

  def fullycontains(other: ContinousRange) = 
    (other.start > start && other.stop < stop)
  

  def contains(other: ContinousRange) =
    (other.start >= start && other.stop <= stop)
  

  def intersect(other: ContinousRange): Option[ContinousRange] = {
    if (this == other) {
      Some(new ContinousRange(start, stop))
    } else {
      var nstart = start.max(other.start)
      var nstop = stop.min(other.stop)
      
      if (nstop > nstart) {
        Some(new ContinousRange(nstart, nstop))
      } else {
        None
      }
    }
  }
  def magnitude = stop - start

  override def toString = "ContinousRange(%f, %f)".format(start, stop)
}
