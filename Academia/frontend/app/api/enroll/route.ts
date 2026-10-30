import { cookies } from "next/headers";

export async function POST(request: Request) {
    const { studentId, classId } = await request.json();

    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value

    const response = await fetch(`http://localhost:8080/enrollments/enroll?studentId=${studentId}&classId=${classId}`, {
        method: "POST",
        headers: { Authorization: `Bearer ${token}` }
    })

    if (!response.ok) {
        const errorText = await response.text();
        return Response.json({ error: errorText }, { status: response.status });
    }

    const message = await response.text();
    return Response.json({ message });
}