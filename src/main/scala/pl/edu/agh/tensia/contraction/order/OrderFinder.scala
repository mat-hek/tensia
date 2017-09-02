package pl.edu.agh.tensia.contraction.order

import pl.edu.agh.tensia.contraction.order.tree.Tree
import pl.edu.agh.tensia.tensor.Tensor

/**
  * Created by mathek on 04/06/2017.
  */
trait OrderFinder {
  def findContractionOrder[T, C](tensors:Seq[Tensor[T]]):Tree[T]
}
