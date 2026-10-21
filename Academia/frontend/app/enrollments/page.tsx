import { cookies } from "next/headers";

interface Enrollment {
    id: string;
    registrationDate: string;
    studentId: string;
    classId: string;
    classType: string;
    classSchedule: string; 
}

async function getEnrollments(): Promise<Enrollment[]> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    //Pega o id do aluno
    const meResponse = await fetch("http://localhost:8080/students/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    const student = await meResponse.json();

    const enrollmentsResponse = await fetch(`http://localhost:8080/enrollments/student/${student.id}`, {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return enrollmentsResponse.json();
}

export default async function EnrollmentsPage() {
    const enrollments = await getEnrollments();

    return (
        <div>
            <h1>Enrolled Classes</h1>
            {enrollments.map((enrollment) => (
                <div key={enrollment.id}>{enrollment.classType}</div>
            ))}
        </div>
    );
}