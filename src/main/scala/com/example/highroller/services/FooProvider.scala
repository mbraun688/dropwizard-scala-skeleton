package com.example.highroller.services

import javax.ws.rs.core.Context
import javax.ws.rs.ext.Provider

import com.sun.jersey.api.core.HttpContext
import com.sun.jersey.core.spi.component.{ComponentContext, ComponentScope}
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable
import com.sun.jersey.spi.inject.{Injectable, InjectableProvider}
import java.lang.reflect.Type

import scala.util.Random

@Provider
class FooProvider extends AbstractHttpContextInjectable[Foo] with InjectableProvider[Context, Type] {
  override def getValue(httpContext: HttpContext): Foo = {
    val random = new Random().nextInt(5000)
    Foo(random)
  }

  override def getInjectable(componentContext: ComponentContext, a: Context, c: Type): Injectable[Foo] = {
    if (c.equals(classOf[Foo])) this else null
  }

  override def getScope = ComponentScope.PerRequest
}

case class Foo(randomVal: Int)