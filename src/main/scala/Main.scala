/**
  * Created by mathek on 31/03/2017.
  */

import akka.actor.{ActorSystem, Props}
import pl.edu.agh.tensia.computation.ComputationNode
import pl.edu.agh.tensia.tensor._
import pl.edu.agh.tensia.helpers._
import pl.edu.agh.tensia.contraction_order.{BFSAlg, ContractedDims}

object Main extends App {
  val t = Seq(Tensor.rand(3, 4), Tensor.rand(4, 5), Tensor.rand(2, 3, 5))
  val contTree = BFSAlg.findContractionOrder(
    t,
    mkContractedDims((t(0), t(1)) -> Seq((1, 0)), (t(0), t(2)) -> Seq((0, 1)), (t(1), t(2)) -> Seq((1, 2)))
  )
  val compTree = contTree.toCompTree
  val system = ActorSystem("system")

  system.actorOf(Props(new ComputationNode(compTree)))
}
