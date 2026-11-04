import { cookies } from "next/headers";
import { getStudentInfo } from "@/lib/getStudentInfo";
import { redirect } from "next/navigation";
import EnrollButton from "./EnrollButton";

interface GymClass {
    id: string;
    typeClass: string;
    schedule: string;
    instructorId: string;
    studentsLimit: number;
    classStatus: string;
    enrolledCount: number;
}

async function fetchAvailableClasses(): Promise<GymClass[]> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        redirect("/login");
    }

    const response = await fetch("http://localhost:8080/students/classes/available", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    if (!response.ok) {
        redirect("/login");
    }

    return response.json();
}

export default async function AvailableClassesPage() {
    const student = await getStudentInfo();
    const classes = await fetchAvailableClasses();

    return (
        <div>
            <h1>Classes Available</h1>
            {!student.planActive && <p className="text-red-600">Your plan is expired. Renew it to enroll.</p>}
            {classes.map((classe) => (
                <div key={classe.id}>
                    <div>{classe.typeClass}</div>
                    <div>{classe.schedule}</div>
                    <div>{classe.enrolledCount} of {classe.studentsLimit}</div>
                    <EnrollButton classId={classe.id} studentId={student.id} disabled={!student.planActive} />
                </div>
            ))}
        </div>
    );
}
