/*
 * Copyright 2020 Spotify AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package magnolify.protobuf

import magnolify.shared._
package object unsafe {
  implicit val pfByte: ProtobufField[Byte] = ProtobufField.from[Int](_.toByte)(_.toInt)
  implicit val pfChar: ProtobufField[Char] = ProtobufField.from[Int](_.toChar)(_.toInt)
  implicit val pfShort: ProtobufField[Short] = ProtobufField.from[Int](_.toShort)(_.toInt)

  object Proto3Option {
    implicit val proto3Option: ProtobufOption = new ProtobufOption.Proto3Option
  }

  implicit def pfUnsafeEnum[T: EnumType]: ProtobufField[UnsafeEnum[T]] =
    ProtobufField
      .from[String](s => Option(s).filter(_.nonEmpty).map(UnsafeEnum.from[T]).orNull)(
        UnsafeEnum.to[T]
      )
}
