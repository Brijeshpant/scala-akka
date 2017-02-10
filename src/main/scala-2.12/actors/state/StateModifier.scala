package actors.state

import akka.actor.{Actor, ActorRef, Props}

/**
  * Created by bpant on 09/02/17.
  */
object StateModifier {
  def props(statfulActor:ActorRef):Props = Props(new StateModifier(statfulActor))
}
class StateModifier(statfulActor: ActorRef) extends Actor {
  override def receive: Receive = {
    case IncrementBy(value) => statfulActor ! IncrementBy(value)

    case DecrementBy(value) => {
      statfulActor ! IncrementBy(value)
    }
    case GetCurrentState => statfulActor ! GetCurrentState
    case CurrentState(value) => {
      println(s"value received from $self is $value")
      context.stop(statfulActor)
    }
  }
}
