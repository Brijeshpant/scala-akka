package actors.state

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * Created by bpant on 09/02/17.
  */
object StateTest extends App {
  private var system: ActorSystem = ActorSystem("my-system")
  private var statefulActor: ActorRef = system.actorOf(Props[StatefulActor], "statefulActor")
  private var stateModifierActor1: ActorRef = system.actorOf(StateModifier.props(statefulActor), "statefulActor1")
  private var stateModifierActor2: ActorRef = system.actorOf(StateModifier.props(statefulActor), "statefulActor2")

  stateModifierActor1 ! GetCurrentState
  stateModifierActor1 ! IncrementBy(30)
  stateModifierActor1 ! IncrementBy(30)
  stateModifierActor1 ! GetCurrentState

  stateModifierActor1 ! GetCurrentState


  stateModifierActor2 ! IncrementBy(30)

}
