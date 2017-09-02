package pl.edu.agh.tensia.contraction.order

import pl.edu.agh.tensia.contraction.order.native.OrderFinderResult
import pl.edu.agh.tensia.helpers.loadLib
import pl.edu.agh.tensia.tensor._

/**
  * Created by mathek on 03/06/2017.
  */

object BFSOrderFinder extends OrderFinder{
  loadLib("contraction_order_finder_BFS")
  @native def ord(dimensionsSizes:Array[Int], contractedDimsSizes:Array[Array[Int]]):OrderFinderResult =
    throw new Error("jni fail")

  override def findContractionOrder[T, C](tensors:Seq[Tensor[T]]) = {
    val dimsSizes = tensors map (_.dimensions.totalSize) toArray
    val contractedDimsSizes: Array[Array[Int]] = Array.fill(tensors.length, tensors.length)(1)
    for ((t1, i) <- tensors.zipWithIndex; (t2, j) <- tensors.zipWithIndex; if t1 != t2) {
      val contractedDims: Dimensions = t1.dimensions intersect t2.dimensions
      contractedDimsSizes(i)(j) = contractedDims.totalSize
    }
    ord(dimsSizes, contractedDimsSizes) toContractionTree tensors.toIndexedSeq
  }
}
