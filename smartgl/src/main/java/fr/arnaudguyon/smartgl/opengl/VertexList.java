/*
    Copyright 2016 Arnaud Guyon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package fr.arnaudguyon.smartgl.opengl;

/**
 * List of Vertex for a Face3D
 */
public class VertexList extends AttribList {

	public VertexList() {
		super(3); // 3 floats per vertex (x,y,z)
	}

	public VertexList(VertexList other) {
		super(other);
	}
	
	public VertexList(float[] other, int otherElementsPerVector) {
		super(other, otherElementsPerVector, 3);
	}

}
