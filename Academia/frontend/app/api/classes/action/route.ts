import { cookies } from "next/headers";

export async function PATCH(request: Request) {
    const { classId, action } = await request.json();

    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        return Response.json({ error: "Unauthorized" }, { status: 401 });
    }

    if (action !== "finish" && action !== "start") {
        return Response.json({ error: "Invalid action" }, { status: 400 });
    }

    if (!classId) { 
        return Response.json({ error: "Class ID is required" }, { status: 400 });  
    }

    const response = await fetch(`http://localhost:8080/classes/${classId}/${action}`, {
        method: "PATCH",
        headers: { Authorization: `Bearer ${token}` },
    });

    if (!response.ok) {
        const errorText = await response.text();
        return Response.json({ error: errorText || "An error occurred" }, { status: response.status });
    }

    const message = await response.text();
    return Response.json({ message });
}