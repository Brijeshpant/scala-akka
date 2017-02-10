package actors.state

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by bpant on 09/02/17.
  */
case class IncrementBy(value:Int)
case class DecrementBy(value:Int)
case class GetCurrentState()
case class CurrentState(state:Int)

class StatefulActor extends Actor{
  var state: Int =0
  override def receive: Receive = {
    case IncrementBy(value) => {
      state +=value
    }
    case DecrementBy(value) => {
      state -=value
    }
    case  GetCurrentState =>  sender ! CurrentState(state)
  }
}
