package pl.edu.agh.tensia.graphml

import org.scalatest.{FunSpec, Matchers}
import pl.edu.agh.tensia.TensorNetwork
import pl.edu.agh.tensia.tensor.NDTensor

import scala.xml._

class GraphMLParserTest extends FunSpec with Matchers {

  describe("GraphMLParserTest") {

    it("should parseNodes") {
      val graph =
        <graph>
          <node id="a">
            <data key="shape">2,3</data>
            <data key="dataPath">/path/to/data</data>
          </node>
          <node id="b">
            <data key="shape">3,4</data>
            <data key="dataPath">/path/to/data</data>
          </node>
          <edge id="e1" source="a" target="b">
            <data key="srcDim">1</data>
            <data key="destDim">0</data>
          </edge>
        </graph>

      val res = GraphMLParser.parseNodes(graph \ "node")

      res.get("a") should not be empty
      res.get("b") should not be empty
      //TODO: more checks
    }

    it("should parseNode") {
      val node =
        <node>
          <data key="shape">2,2</data>
          <data key="dataPath">/path/to/data</data>
        </node>

      val res = GraphMLParser.parseNode(node)
      res.dims should have length 2
      res.dims(0) should have size 2
      res.dims(1) should have size 2
      res.dims(0) should not equal res.dims(1)

      res.dataPath should be ("/path/to/data")
    }

    it("should parseEdges") {
      val graph =
        <graph>
          <edge id="e0" source="n0" target="n1">
            <data key="srcDim">1</data>
            <data key="destDim">2</data>
          </edge>
        </graph>

      val res = GraphMLParser.parseEdges(graph \ "edge")
      res should have length 1
      res.head should equal (EdgeNode("n0", 1, "n1", 2))
    }

    it("should create tensorNetworkFromXML") {
      val graph =
        <graph>
          <node id="a">
            <data key="shape">2,3</data>
            <data key="dataPath">/path/to/data</data>
          </node>
          <node id="b">
            <data key="shape">3,4</data>
            <data key="dataPath">/path/to/data</data>
          </node>
          <edge id="e1" source="a" target="b">
            <data key="srcDim">1</data>
            <data key="destDim">0</data>
          </edge>
        </graph>

      val res: TensorNetwork[NDTensor] = GraphMLParser.tensorNetworkFromXML(graph)

      res.tensors should have length 2
      //TODO: Check result
    }

  }
}
