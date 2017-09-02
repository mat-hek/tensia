package pl.edu.agh.tensia.contraction

import pl.edu.agh.tensia.tensor.Tensor

/**
  * Created by mathek on 04/06/2017.
  */
package object order {
  implicit def tensorToTreeLeaf[T, C](tensor: Tensor[T]):TreeLeaf[T] = TreeLeaf(tensor)
}
